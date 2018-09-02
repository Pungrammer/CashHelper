package at.fikar.raphael.cashhelper.injection;

import at.fikar.raphael.cashhelper.gui.javafx.tables.EntryTable;
import at.fikar.raphael.cashhelper.injection.factories.IEntryTableFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class TableModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(EntryTable.class, EntryTable.class)
                .build(IEntryTableFactory.class));
    }
}
