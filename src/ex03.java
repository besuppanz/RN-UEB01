import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ex03 {
    private static final int versionField = 2;

    public static void main(String[] args) {

        createMsg(true, true, 16, new byte[] {1,2,3,4,5});
    }

    private static byte[] createMsg(boolean isData, boolean isUrgent, int sequenceNumber, byte[] payload) throws IllegalArgumentException {

        int payloadLength = payload.length;

        if((sequenceNumber > Math.pow(2,16)-1)) {
            throw new IllegalArgumentException("Sequence number cannot be greater than 2^16-1");
        } else if(payload == null || payloadLength < 1) {
            throw new IllegalArgumentException("Payload cannot be empty");
        }

        //data + 8 Byte overhead (64 Bit = 5 Bit version field, 9 Bit reserved, 2 Bit Flags, 16 Bit sequence number, 32 Bit payload length)
        ByteBuffer byteBuffer = ByteBuffer.allocate(payloadLength+8);

        //Print
        for(byte b : byteBuffer.array()) {
            System.out.print(Integer.toBinaryString(b & 255 | 256).substring(1));
        }
        System.out.println();

        //Add version number to Byte Buffer, shift to left by three so only first 5 bit are used instead of first 8 bit
        byteBuffer.put((byte) (versionField<<3));

        //Print
        for(byte b : byteBuffer.array()) {
            System.out.print(Integer.toBinaryString(b & 255 | 256).substring(1));
        }
        System.out.println();

        //Create tempByte, initially storing isData Bit and shifting it by one to make space for isUrgent Bit
        byte tempByte = (byte) ((isData ? 1:0) << 1);
        //Add isUrgent Bit
        tempByte = (byte) (tempByte | (isUrgent ? 1:0));
        //Add tempByte to byteBuffer
        byteBuffer.put(tempByte);

        //Print
        for(byte b : byteBuffer.array()) {
            System.out.print(Integer.toBinaryString(b & 255 | 256).substring(1));
        }
        System.out.println();

        //Network Byte Order
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        //Add Sequence Number
        byteBuffer.putShort((short)sequenceNumber);
        //Print
        for(byte b : byteBuffer.array()) {
            System.out.print(Integer.toBinaryString(b & 255 | 256).substring(1));
        }
        System.out.println();

        //Add length of payload
        byteBuffer.putInt(payloadLength);
        //Print
        for(byte b : byteBuffer.array()) {
            System.out.print(Integer.toBinaryString(b & 255 | 256).substring(1));
        }
        System.out.println();

        //Add each Integer of payload
        for(byte b : payload) {
            byteBuffer.put(b);
        }
        //Print
        for(byte b : byteBuffer.array()) {
            System.out.print(Integer.toBinaryString(b & 255 | 256).substring(1));
        }
        System.out.println();

        return byteBuffer.array();
    }
}
