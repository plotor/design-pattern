package com.javapatterns.bridge.peer;

public class Button extends Component {
    private String label;
    /**
     * @link aggregation
     * @directed
     */
    private ButtonPeer lnkButtonPeer;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
