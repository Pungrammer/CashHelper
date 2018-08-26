package at.fikar.raphael.cashhelper.injection;

import at.fikar.raphael.cashhelper.file.FileSource;
import at.fikar.raphael.cashhelper.file.IFileSource;
import com.google.inject.AbstractModule;

public class FileModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IFileSource.class).to(FileSource.class);
    }
}
