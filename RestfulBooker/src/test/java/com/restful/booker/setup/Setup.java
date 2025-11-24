package com.restful.booker.setup;

import com.restful.booker.utils.EnvConfig;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;


import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class Setup {
    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Tester");
        theActorInTheSpotlight().whoCan(CallAnApi.at(EnvConfig.get("BASE_URL")));
    }
}