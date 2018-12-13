package Controller;
import Model.Process;
import Model.BuddyAllocation;
import View.*;
import javafx.application.Application;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Driver {
    public static void main(String[] args) throws Exception{

		MemoryController controller = new MemoryController();
		controller.interact();
	}
}
