package py.edison.megasoftappv2.interfaces;

public interface ItemClickListener<T> {
    void onItemClick(T item);
    void onItemLongClick(T item);
}