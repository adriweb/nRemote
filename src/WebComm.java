// Adriweb - 16/09/2012

public class WebComm {

    public static void encodeAndSend(String msg) throws Exception {

        char output[] = new char[4];
        int state = 1;
        int restbits = 0;
        char[] data = msg.toCharArray();

        Remote.sendEvent("~sin~");  // packet start

        for (int i = 0; i < data.length; i++) {
            int ic = (data[i] >= 0 ? data[i] : (data[i] & 0x7F) + 128);
            switch (state) {
                case 1:
                    output[0] = (char) (192 + (ic >>> 2));
                    Remote.sendCharFromByte((byte) (192 + (ic >>> 2)));
                    restbits = ic & 0x03;
                    break;
                case 2:
                    output[1] = (char) (192 + ((restbits << 4) | (ic >>> 4)));
                    Remote.sendCharFromByte((byte) (192 + ((restbits << 4) | (ic >>> 4))));
                    restbits = ic & 0x0F;
                    break;
                case 3:
                    output[2] = (char) (192 + ((restbits << 2) | (ic >>> 6)));
                    Remote.sendCharFromByte((byte) (192 + ((restbits << 2) | (ic >>> 6))));
                    output[3] = (char) (192 + (ic & 0x3F));
                    Remote.sendCharFromByte((byte) (192 + (ic & 0x3F)));
                    break;
            }

            state = (state < 3 ? state + 1 : 1);
        }

        switch (state) {
            case 2:
                output[1] = (char) (192 + ((restbits << 4)));
                Remote.sendCharFromByte((byte) (192 + ((restbits << 4))));
                output[2] = 190;
                Remote.sendCharFromByte((byte) 190);
                output[3] = 190;
                Remote.sendCharFromByte((byte) 190);
                break;
            case 3:
                output[2] = (char) (192 + ((restbits << 2)));
                Remote.sendCharFromByte((byte) (192 + ((restbits << 2))));
                output[3] = 190;
                Remote.sendCharFromByte((byte) 190);
                break;
        }

        Remote.sendEvent("~sin~");  // packet end
    }

}
