package com.lego.game;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.lego.game.localization.Localizer;
import com.lego.game.utils.ResolutionDescriptor;
import com.lego.mvc.Controller;
import com.lego.mvc.Facade;
import com.lego.mvc.Model;
import com.lego.mvc.core.LegoController;
import com.lego.mvc.core.LegoModel;
import com.lego.mvc.patterns.facade.LegoFacade;

/**
 * Created by sargis on 6/17/15.
 */
public abstract class LegoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ResolutionDescriptor.class).in(Singleton.class);
        bind(Localizer.class).in(Singleton.class);

        //MVC
        bind(Controller.class).to(LegoController.class).in(Singleton.class);
        bind(Model.class).to(LegoModel.class).in(Singleton.class);
        bind(Facade.class).to(LegoFacade.class).in(Singleton.class);
        initView();
    }

    protected abstract void initView();

}
