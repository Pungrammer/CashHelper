package at.fikar.raphael.cashhelper.gui.javafx;

public interface IBuildable<T> {

    /**
     * Builds the FX component of the specified type.
     * @return Completely build FX component of type T.
     */
    T build();

}
