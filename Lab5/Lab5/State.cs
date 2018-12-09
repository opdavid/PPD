using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;
using System.Threading;

namespace Client
{
    public class State
    {
        public const int BUFFER_SIZE = 256;

        // Client socket.  
        public Socket WorkSocket { get; set; }
        // Size of receive buffer.  

        // Receive buffer.  
        public byte[] Buffer { get; set; }
        // Received data string.  
        public StringBuilder sBuilder { get; set; }

        public int Index { get; set; }
        public String Host { get; set; } 

        public State()
        {
            sBuilder = new StringBuilder();
            Buffer = new byte[BUFFER_SIZE];
        }

        public State(String host, int index)
        {
            Host = host;
            Index = index;
            sBuilder = new StringBuilder();
            Buffer = new byte[BUFFER_SIZE];
        }
    }
}

