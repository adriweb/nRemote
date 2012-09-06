import com.ti.et.education.commproxy.INodeID;

/**
 * @author Levak
 */
class CalcItem {
    public INodeID nodeID = null;
    public String SID = "";

    public CalcItem(INodeID nodeID) {
        this.nodeID = nodeID;
        this.SID = Remote.getDeviceInfo(nodeID).getSerialNumber();
    }

    @Override
    public String toString() {
        return this.SID;
    }
}