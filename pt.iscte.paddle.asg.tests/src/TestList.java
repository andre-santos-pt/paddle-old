class Node {
	int element;
	Node next;
}
class IntList {
	Node head;
	Node tail;
}
void init(IntList list) {
	list.head = null;
	list.tail = null;
}
void add(IntList list, int e) {
	Node n;
	n = new Node();
	n.element = e;
	if(list.head == null) {
		list.head = n;
		list.tail = n;
	}
	else {
		list.tail.next = n;
		list.tail = n;
	}
}
boolean exists(IntList list, int e) {
	Node n;
	n = list.head;
	while(n != null) {
		if(n.element == e)
			return true;
		n = n.next;
	}
	return false;
}

