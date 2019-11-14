/**
     * Executes a breadth-first search from an initial state and with a queue/open list/fringe. 
     * @param initial the initial, starting state
     * @param fringe the list of all nodes that should be expanded, 
     * usually empty.
     * @return the solution node if one is found, null otherwise
     */
    //public static Node breadthFirstSearch(State initial, List<Node> fringe) {
    public static Node breadthFirstSearch(State initial, List fringe) {
        // add the initial state to the fringe
        fringe.add(new Node(initial)); 
        // loop through all nodes in the fringe
        while (!fringe.isEmpty()) { // test if fringe is empty, if yes "failure"
            // poll the first node in the list
            Node head=(Node)fringe.get(0);
            fringe.remove(0);
            // pull out the state in the node
            State state=head.getState();
            // examine it to see if it is a goal state
            if (state.goal()) {
                return head; // if goal state then return this node
            }
            // consider using a "closed list" for visited states (avoiding repeated states)
            
            // expand the node, and add all new states to the fringe
            fringe.addAll(Arrays.asList(head.expand())); // a list adds new nodes to the end of the queue
        }
        return null;
    }