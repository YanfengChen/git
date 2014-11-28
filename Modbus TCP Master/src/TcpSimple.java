/**
 * @file TcpSimple.java
 *
 * @if NOTICE
 *
 * Copyright (c) proconX Pty Ltd. All rights reserved. 
 *
 * The following source file constitutes example program code and is
 * intended merely to illustrate useful programming techniques.  The user
 * is responsible for applying the code correctly.
 *
 * THIS SOFTWARE IS PROVIDED BY PROCONX AND CONTRIBUTORS ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL PROCONX OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * @endif
 */


// Import the FieldTalk protocol library
import com.focus_sw.fieldtalk.*;


/**
 * Simple example how to configure a MODBUS/TCP protocol and read values.
 */
public class TcpSimple
{

   /**
    * Main method.
    */
   public static void main(String[] args)
   {
      try
      {
         new TcpSimple().poll();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   /**
    * Poll method.
    */
   public void poll() throws Exception
   {
      MbusTcpMasterProtocol mbus = new MbusTcpMasterProtocol ();

      // Declare as int[] or float[] array to read 32-bit values instead
      short[] dataSet1 = new short[3]/*Number of values to be read*/;

      // Configure and open connection
      mbus.setPort(23);
      mbus.setPollDelay(1000); // Poll only every 1 s
      mbus.openProtocol("192.168.1.175"); // Pass IP address or host name here
      
      try
      {
         while (true)
         {
            try
            {
               // Read data from slave
               mbus.readMultipleRegisters(2 /*Slave address*/,
                                          1 /*Start reference*/,
                                          dataSet1);
               // Simply print the first value read
               System.out.println("Data = " + dataSet1[0]);
            }
            catch (BusProtocolException e)
            {
               e.printStackTrace();
               //System.out.println(e.toString());
            }
         }
      }
      finally
      {
         // Clean-up
         mbus.closeProtocol();
      }
   }
}
