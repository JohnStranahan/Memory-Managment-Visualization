package Model;

abstract class Allocator {
    final void templateMethod() {
        allocateProcess();
        endProcess();
    }

    abstract void allocateProcess();

    abstract void endProcess();
}
