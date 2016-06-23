// half do while loop
// half while loop

text input = ""
number inputSize = 0

loop {
    // do part
    print("Happens any ways. The do part. Also like the very end of the l00p")
    
    inputSize == 4 {
        input == "exit" {
            print("Congradulation you typed exit in four lines")
            stop 3
        }
        print("Sorry but you didnt type exit in 4 turns. Try again")
        input = ""
        inputSize = 0
    }
    
    scan(text, newInput)
    input = input + newInput
    inputSize = inputSize + 1
    
    // while part
    print("While Part")
}