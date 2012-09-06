/**
 * Nspire Remote Controller
 * Adriweb 2012
 * Date: 14/08/12 (first build)
 * Date: 20/08/12 (v1.1)
 * Date: 21/08/12 (v1.2 and v1.3)
 * Date: 22/03/12 (v1.4)
 */

import com.ti.eps.navnet.ConnectionHandle;
import com.ti.eps.navnet.NavNet;
import com.ti.eps.navnet.NodeHandle;
import com.ti.et.education.commproxy.IEvent;
import com.ti.et.education.commproxy.INodeID;
import com.ti.et.education.commproxy.INodeInfo;
import com.ti.et.education.commproxy.NspireVirtualKeyStroke;
import com.ti.et.navnetcommproxy.NavNetCommProxy;

import java.awt.image.BufferedImage;
import java.io.File;

public class Remote {

    static NavNetCommProxy nncp = null;
    static INodeID[] theCalcs = null;

    static File tmpDir;

    static {
        String tempPath = System.getProperty("java.io.tmpdir");
        tmpDir = new File(tempPath);
    }

    public static void Initialize() throws Exception {
        System.out.println("initialize");
        nncp = NavNetCommProxy.init(tmpDir, "", 1);
        System.out.println("nncp inited");
        nncp = NavNetCommProxy.getInstance();
        System.out.println("nncp instanced");
        Thread.sleep(1000);
        connect();
    }

    public static void connect() throws Exception {
        int numberOfCalcs = getNumberOfDevices();
        System.out.println(numberOfCalcs + " device(s) connected");
        theCalcs = nncp.getConnectedNodes();
    }

    static int getNumberOfDevices() {
        if (nncp != null) {
            return nncp.getConnectedNodes().length;
        } else {
            return 0;
        }
    }

    static INodeInfo getDeviceInfo(INodeID nodeID) {
        try {
            return nncp.getNodeInfo(nodeID); 
        } catch (Exception e) {
            return null;
        }
    }

    static BufferedImage getScreen(INodeID nodeID) {
        try {
            return (BufferedImage) nncp.getScreen(nodeID, true);
        } catch (Exception e) {
            return null;
        }
    }

    public static void sendEvent(String keyStr, INodeID nodeID) throws Exception {
        NodeHandle hdl = nncp.getHandle(nodeID);
        sendEventToNode(hdl, new NspireVirtualKeyStroke(keyStr));
    }

    public static void sendEvent(String keyStr) throws Exception {
        NodeHandle hdl;
        if (theCalcs == null) {
            System.out.println("No calc(s) to send events to.");
            return;
        }
        for (INodeID nodeID : theCalcs) {
            hdl = nncp.getHandle(nodeID);
            sendEventToNode(hdl, new NspireVirtualKeyStroke(keyStr));
            // sleep needed ? I don't have enough calcs to test a classroom setup....
        }
    }

    public static void sendString(String str) throws Exception {
        for (char ch : str.toCharArray()) {
            sendEvent(Character.toString(ch));
        }
    }

    public static int sendEventToNode(NodeHandle calcHandle, IEvent event)
            throws Exception {
        int status = 0;
        if (calcHandle != null) {
            NspireVirtualKeyStroke key = (NspireVirtualKeyStroke) event;
            byte[] keyBytesCode = key.getKeyCode();

            if (keyBytesCode != null) {
                ConnectionHandle ch = new ConnectionHandle();
                status = NavNet.connect(calcHandle, 16450, ch);

                if (status == 1) {
                    status = NavNet.write(ch, NspireVirtualKeyStroke.VIRTUAL_KEY_STROKE_EVENT_COMMAND, NspireVirtualKeyStroke.VIRTUAL_KEY_STROKE_EVENT_COMMAND.length);

                    if (status == 1) {
                        byte[] keyEvent = {0, 0, 0, 0, (byte) (key.getEventType() & 0xFF), 2, (byte) (keyBytesCode[0] & 0xFF), 0, (byte) (keyBytesCode[1] & 0xFF), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, (byte) (keyBytesCode[2] & 0xFF), 0};
                        status = NavNet.write(ch, keyEvent, keyEvent.length);
                        Thread.sleep(60L);
                    }
                    NavNet.disconnect(ch);
                }
            }
        }
        return status;
    }

    public static void sendFile(INodeID nodeID, String computerPath, String calcPath)
            throws Exception {
        if (nodeID != null)
            nncp.sendFileToNode(nodeID, computerPath, calcPath, null);
    }

}