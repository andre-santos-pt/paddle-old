class IntStack {
	int[] elements;
	int next;
}
void init(IntStack stack, int size) {
	stack.elements = new int[size];
	stack.next = 0;
}
void push(IntStack stack, int e) {
	stack.elements[stack.next] = e;
	stack.next = stack.next + 1;
}
int pop(IntStack stack) {
	int t;
	t = stack.elements[stack.next - 1];
	stack.next = stack.next - 1;
	return t;
}

