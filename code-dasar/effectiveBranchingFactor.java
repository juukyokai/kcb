/**
     * Calculate the effective branching factor for a tree search.
     * Adapted from ATILLA DEMIRAY's ANSI C CODE CALCULATING EFFECTIVE BRANCHING FACTOR
     * http://utopia.poly.edu/~ademir02/soft/sources/aisx/ebfx.htm (was available 30 Aug 2005)
     * This Java code is taken "as is" from Artificial Intelligence course sample codes in Assignment
     * from The University of Queensland, Australia
     * @param nNodes number of nodes
     * @param depth depth where solution was found
     * @return the estimated effective branching factor
     */
    public static double effectiveBranchingFactor(int nNodes, int depth) {
        double maxError=0.01; // the maximum error we accept from a solution 
        double delta=0.01;    // how much we change our tested ebf each iteration   
        int signOfError=0;    // the sign of the difference between N+1 and 1+b+b^2+......+b^d
        double b=0;           // search for the optimum b will start from minimum possible b
        while (true) {        // search for b starts here
            // compute the expression 1+b+b^2+......+b^d
            double sum=1;
            for (int i=1; i<depth+1; i++) {
                sum+=Math.pow(b, (double)i);
            }
            // now the tricky bit, we compute the difference between 
            // N+1 and 1+b+b^2+......+b^d, remember that we should have N+1=1+b+b^2+......+b^d
            double error=sum-(1+(double)nNodes);
            // save previous sign of error
            int signOfPreviousError=signOfError;
            // determine the new sign of error
            if (error<0.0) // negative
                signOfError=-1;
            else // positive
                signOfError=+1;
            /* if the error calculated above is greater than the maximum acceptable error, then check if sign of error
               was changed, if so that means loop missed the root that we are looking for, then decrease b by delta and 
               decrease delta to catch root in next search if sign of error wasnt change then increase 'b' by delta
               otherwise if error is smaller than the limit return the effective branch factor */
            if (Math.abs(error)>maxError) { // error is big
                if (signOfPreviousError==signOfError || signOfPreviousError==0) {  
                    b+=delta;   // taking another step towards solution
                } else {        // change of sign which means that we jumped over the minima
                    b-=delta;   // go back
                    delta/=2;   // take smaller steps
                    signOfError=signOfPreviousError;  // undo change of sign
                }
            } else // error is small, let's return current estimate
                return(b);
        }
    }