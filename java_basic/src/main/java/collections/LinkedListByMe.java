package collections;

/**
 * 手写LinkedList
 *
 * @author walker
 * @date 2020-05
 */
public class LinkedListByMe<E> {

    transient int size = 0;

    transient Node<E> first;

    transient Node<E> last;

    public static class Node<E> {
        E element;
        Node<E> pre;
        Node<E> next;

        Node(Node<E> pre, E element, Node<E> next) {
            this.pre = pre;
            this.element = element;
            this.next = next;
        }
    }

    // [1, 2, *]

    /**
     * add方法
     *
     * @param e 元素
     */
    private void linkedLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    Node<E> node(int index) {
        checkIndexPosition(index);
        if (index > (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.pre;
            }
            return x;
        }
    }

    public Node<E> get(int index) {
        checkIndexPosition(index);
        return node(index);
    }

    private void linkedBefore(int index, E e) {

    }

    public void add(E e) {
        linkedLast(e);
    }

    public void add(int index, E e) {
        checkIndexPosition(index);
        if (index == size) {
            linkedLast(e);
            return;
        }
        linkedBefore(index, e);
    }

    private void checkIndexPosition(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("非法参数");
        }
    }
}
