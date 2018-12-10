using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Client
{
    public class Program
    {
        public static List<string> hosts = new List<string> { "www.google.com", "www.wikipedia.com", "www.facebook.com", "www.msdn.microsoft.com" };
        public static DateTime start;
        static void Main(string[] args)
        {
            EventDownload();
            TaskDownload();
            AwaitDownload().GetAwaiter().GetResult();
            Console.WriteLine("\n\n");
            Console.ReadLine();
        }

        public static void EmptyLists()
        {
            Client.connectDone = new List<ManualResetEvent>();
            Client.sendDone = new List<ManualResetEvent>();
            Client.receiveDone = new List<ManualResetEvent>();
            Client.responses = new List<String>();
        }

        public static void AddEvents()
        {
            Client.connectDone.Add(new ManualResetEvent(false));
            Client.sendDone.Add(new ManualResetEvent(false));
            Client.receiveDone.Add(new ManualResetEvent(false));
            Client.responses.Add(String.Empty);

        }
        public static void EventDownload()
        {
            EmptyLists();
            Console.WriteLine("Download with callbacks ===============\n");
            start = DateTime.Now;
            for (int i = 0; i < hosts.Count; i++)
            {
                AddEvents();
                Console.WriteLine(Client.StartClient(new State(hosts[i], i)));
            }
            Console.WriteLine(String.Format("\tDuration: {0}\n", DateTime.Now - start));
        }

        public static void TaskDownload()
        {
            Console.WriteLine("Download with tasks ====================\n");
            List<Task> tasks = new List<Task>();
            EmptyLists();
            start = DateTime.Now;
            for (int i = 0; i < hosts.Count; i++)
            {
                int index = 0 + i;
                AddEvents();
                tasks.Add(Task.Run(() => {
                    Console.WriteLine(Client.StartClient(new State(hosts[index], index)));
                }));
            }

            Task.WaitAll(tasks.ToArray());
            Console.WriteLine(String.Format("\tDuration: {0}\n", DateTime.Now - start));
        }

        public static async Task AwaitDownload()
        {
            Console.WriteLine("Download with async await ======================\n");
            List<Task> tasks = new List<Task>();
            EmptyLists();
            start = DateTime.Now;
            for (int i = 0; i < hosts.Count; i++)
            {
                int index = 0 + i;
                AddEvents();
                String result = await Task.Run(() => {
                    return Client.StartClient(new State(hosts[index], index));
                });
                Console.WriteLine(result);
            }
            Console.WriteLine(String.Format("\tDuration: {0}\n", DateTime.Now - start));
        }
    }
}
