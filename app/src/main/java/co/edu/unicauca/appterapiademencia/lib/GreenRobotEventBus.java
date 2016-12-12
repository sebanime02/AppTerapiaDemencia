package co.edu.unicauca.appterapiademencia.lib;

import java.util.List;

import co.edu.unicauca.appterapiademencia.domain.Patient;

/**
 * Created by SEBAS on 18/10/2016.
 */

public class GreenRobotEventBus implements EventBus {
    de.greenrobot.event.EventBus eventBus;

    private static class SingletonHolder{
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();

    }
    public static GreenRobotEventBus getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public GreenRobotEventBus(){
        this.eventBus = de.greenrobot.event.EventBus.getDefault();
    }

    @Override
    public void register(Object suscriber) {
        eventBus.register(suscriber);

    }

    @Override
    public void unregister(Object suscriber) {
        eventBus.unregister(suscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);

    }
    public void postSticky(Object event)
    {
        eventBus.postSticky(event);
    }

    @Override
    public void removeSticky(Object event) {
        eventBus.removeStickyEvent(event);

    }

    @Override
    public void removeAllSticky() {
        eventBus.removeAllStickyEvents();
    }


}
