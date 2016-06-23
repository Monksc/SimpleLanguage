number base = 2
number exp = 0
number stopAt = 1024

loop {
    
    stopAt == base ^ exp {
        print("log base " + base + " of " + stopAt + " is " + exp)
        stop 2
    }
    
    exp = exp + 1
}