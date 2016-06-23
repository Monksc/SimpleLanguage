number i = 3

loop {
    
    print(" . . . . . . . . i is " + i)
    
    number j = 3
    loop {
        
        print("j is " + j)
        
        j == 0 {
            
            i == 0 {
                stop 4
            }
        
            stop 2
        }
        
        j = j - 1
    }
    
    i = i - 1
}

print("END")