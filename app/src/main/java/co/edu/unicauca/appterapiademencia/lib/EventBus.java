package co.edu.unicauca.appterapiademencia.lib;

/**
 * Created by SEBAS on 18/10/2016.
 */

public interface EventBus {
    //Tipo de dato objeto lo hace generico a cualquier interfaz que maneje el presentador
    void register(Object suscriber);
    void unregister(Object suscriber);
    void post(Object event);
    void postSticky(Object event);
    void removeSticky(Object event);
    void removeAllSticky();
    void registerSticky(Object event);


}
