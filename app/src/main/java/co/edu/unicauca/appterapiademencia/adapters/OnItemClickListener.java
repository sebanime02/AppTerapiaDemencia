package co.edu.unicauca.appterapiademencia.adapters;

import co.edu.unicauca.appterapiademencia.domain.User;

/**
 * Created by ENF on 28/10/2016.
 */

public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
