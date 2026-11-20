import java.util.EmptyStackException;

/** Массиваар хэрэгжүүлсэн стек (сурах бичиг) */
public class ArrayStack implements Stack {
    protected int top = -1;
    protected Object[] stack;

    public ArrayStack(int initialCapacity) {
        if (initialCapacity < 1) throw new IllegalArgumentException("initialCapacity must be >= 1");
        stack = new Object[initialCapacity];
    }
    public ArrayStack() { this(10); }

    public boolean empty() { return top == -1; }

    public Object peek() {
        if (empty()) throw new EmptyStackException();
        return stack[top];
    }

    public void push(Object theElement) {
        if (top == stack.length - 1) {
            Object[] n = new Object[2 * stack.length];
            System.arraycopy(stack, 0, n, 0, stack.length);
            stack = n;
        }
        stack[++top] = theElement;
    }

    public Object pop() {
        if (empty()) throw new EmptyStackException();
        Object t = stack[top];
        stack[top--] = null;
        return t;
    }
}
