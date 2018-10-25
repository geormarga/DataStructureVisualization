package Models.Interfaces;

import java.util.List;

public interface IVisible<T> {
    List<T> displayAllAsList();
//    Node should be displayed in a certain way,then some controllers should do the work of handling the changes in each data structure (each list)
}
