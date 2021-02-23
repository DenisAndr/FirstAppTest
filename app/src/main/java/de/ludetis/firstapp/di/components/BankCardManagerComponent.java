package de.ludetis.firstapp.di.components;

import javax.inject.Singleton;

import dagger.Component;
import de.ludetis.firstapp.AddCardActivity;
import de.ludetis.firstapp.BankCardsManager;
import de.ludetis.firstapp.DetailFragment;
import de.ludetis.firstapp.EditablDetailFragment;
import de.ludetis.firstapp.MainActivity;
import de.ludetis.firstapp.di.modules.BankCardManagerModule;

@Singleton
@Component (modules = {BankCardManagerModule.class})
public interface BankCardManagerComponent {

    void inject(BankCardsManager obj);
    void inject(AddCardActivity obj);
    void inject(DetailFragment obj);
    void inject(EditablDetailFragment obj);
    void inject(MainActivity obj);

}
