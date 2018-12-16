package Model;

public class FindSmallest
{
    //This method is a helper method for the allocate
    //function which finds the correct node to split
    //while searching for a block of memory that will most
    //appropriately hold the process.
    //
    //@return the smallest non-allocated memoryNode in the list.
    private BuddyAllocation b;

    public FindSmallest(BuddyAllocation b){this.b = b;}


    public MemoryNode findSmall()
    {
        MemoryNode search = b.getMNode();

        if((search.getPrevious() == null) && (search.getNext() == null)){
            return search;
        }

        MemoryNode smallest = search;
        boolean found = false;

        while((search.getNext() != null) && (found != true)){
            MemoryNode x = search.getNext();

            if((search.getAllocationArray().length > x.getAllocationArray().length) && (x.isAllocated() == false)){
                smallest = x;
                found = true;
            }

            search = x;
        }

        return smallest;
    }

    public void allocateProcess(int Size)
    {
        boolean bestSize = false;
        int bestFit = findBestFit(Size);

        //now find node to allocate process
        MemoryNode imDumb = null;
        MemoryNode toAllocate = null;
        int iterations = 1;

        while(bestSize == false){
            imDumb = b.getMNode();
            while(imDumb != null && bestSize == false){
                if((imDumb.getAllocationArray().length == bestFit) && (imDumb.isAllocated() == false)){
                    bestSize = true;
                    toAllocate = imDumb;
                }
                else{
                    imDumb = imDumb.getNext();
                }
            }
            if(bestSize == false){
                imDumb = b.getMNode();
                System.out.println("Split to fit!");
                MemoryNode s = findSmall();
                if(s.isAllocated() == false){
                    b.splitArray(findSmall(),null);
                }
                else{
                    System.out.println("Here");
                    int x = Size;
                    boolean hasSplit = false;
                    while(x <= 256 && hasSplit == false){
                        x = x*2;
                        x = findBestFit(x);
                        System.out.println("Best fit is: " + x);
                        imDumb = b.getMNode();
                        while(imDumb != null && hasSplit == false){
                            if(imDumb.getAllocationArray().length == x &&
                                    imDumb.isAllocated() == false){
                                hasSplit = true;
                                System.out.println("Found it!");
                                System.out.println("Splitting " + imDumb.getAllocationArray().length);
                                b.splitArray(imDumb,null);

                            }
                            else{
                                imDumb = imDumb.getNext();
                            }
                        }
                    }
                }
                imDumb = b.getMNode();
            }
            iterations++;
            if(iterations > 4){
                break;
            }
        }


        if(bestSize == true){

            System.out.println("The best fit node is " + toAllocate + " of size " + toAllocate.getAllocationArray().length);
            toAllocate.allocate(Size);
        }
        else{
            System.out.println("There is no node big enough for that process currently.");
        }


    }

    public int findBestFit(int Size){

        boolean bestSize = false;
        int bestFit = 0;
        //First find the best size of memory for the process
        for(int i = 256; i >= 2 && bestSize == false; i= i/2){
            if((i - Size) < Size){
                bestSize = true;
                bestFit = i;
            }
        }

        return bestFit;
    }

}
