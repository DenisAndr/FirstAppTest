package de.ludetis.firstapp;

import android.app.Application;

import de.ludetis.firstapp.di.components.BankCardManagerComponent;
import de.ludetis.firstapp.di.components.DaggerBankCardManagerComponent;
import de.ludetis.firstapp.di.modules.BankCardManagerModule;

public class MyApp extends Application {

    private static BankCardManagerComponent bankCardManagerComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        bankCardManagerComponent = DaggerBankCardManagerComponent.builder()
                .bankCardManagerModule(new BankCardManagerModule(this)).build();

    }

    public static BankCardManagerComponent getBankCardManagerComponent() {
        return bankCardManagerComponent;
    }
}
