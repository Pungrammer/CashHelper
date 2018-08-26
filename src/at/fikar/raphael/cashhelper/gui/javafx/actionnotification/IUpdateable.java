package at.fikar.raphael.cashhelper.gui.javafx.actionnotification;

/**
 * Interface for all Scenes and Stages which have inter-dependend data and need to be notified about any changes.
 */
public interface IUpdateable {

    /**
     * Returns the correct notification type.
     * @return correct notification type
     */
    UpdateNotificationType getUpdateType();

    /**
     * Updates the content of this Scene/Stage
     */
    void updateContent();
}
