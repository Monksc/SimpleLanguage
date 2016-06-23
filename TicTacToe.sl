number X = 0
number O = 1
number EMPTY = 0-1

number position1 = EMPTY
number position2 = EMPTY
number position3 = EMPTY
number position4 = EMPTY
number position5 = EMPTY
number position6 = EMPTY
number position7 = EMPTY
number position8 = EMPTY
number position9 = EMPTY

number turns = 0

number position = 0

loop {

    // Print Board
    position1 == X {
        text top = "X"
    }
    position1 == O {
        text top = "O"
    }
    position1 == EMPTY {
        text top = " "
    }
    
    position2 == X {
        top = top + "|X|"
    }
    position2 == O {
        top = top + "|O|"
    }
    position2 == EMPTY {
        top = top + "| |"
    }
    
    position3 == X {
        top = top + "X"
    }
    position3 == O {
        top = top + "O"
    }
    position3 == EMPTY {
        top = top + " "
    }
    
    print(top)
    print("_____")
    
    position4 == X {
        text middle = "X"
    }
    position4 == O {
        text middle = "O"
    }
    position4 == EMPTY {
        text middle = " "
    }
    
    position5 == X {
        middle = middle + "|X|"
    }
    position5 == O {
        middle = middle + "|O|"
    }
    position5 == EMPTY {
        middle = middle + "| |"
    }
    
    position6 == X {
        middle = middle + "X"
    }
    position6 == O {
        middle = middle + "O"
    }
    position6 == EMPTY {
        middle = middle + " "
    }
    
    print(middle)
    print("_____")
    
    position7 == X {
        text bottom = "X"
    }
    position7 == O {
        text bottom = "O"
    }
    position7 == EMPTY {
        text bottom = " "
    }
    
    position8 == X {
        bottom = bottom + "|X|"
    }
    position8 == O {
        bottom = bottom + "|O|"
    }
    position8 == EMPTY {
        bottom = bottom + "| |"
    }
    
    position9 == X {
        bottom = bottom + "X"
    }
    position9 == O {
        bottom = bottom + "O"
    }
    position9 == EMPTY {
        bottom = bottom + " "
    }
    
    print(bottom)



    // horizonatal wins
    position1 == position2 {
        position2 == position3 {
            position1 == X {
                print("X Won")
                stop 4
            }
            position1 == O {
                print("O Won")
                stop 4
            }
        }
    }
    
    position4 == position5 {
        position5 == position6 {    
            position4 == X {
                print("X Won")
                stop 4
            }
            position4 == O {
                print("O Won")
                stop 4
            }
        }
    }
    
    position7 == position8 {
        position8 == position9 {    
            position7 == X {
                print("X Won")
                stop 4
            }
            position7 == O {
                print("O Won")
                stop 4
            }
        }
    }
    
    
    // vertical wins
    position1 == position4 {
        position4 == position7 {    
            position1 == X {
                print("X Won")
                stop 4
            }
            position1 == O {
                print("O Won")
                stop 4
            }
        }
    }
    
    position2 == position5 {
        position5 == position8 {    
            position2 == X {
                print("X Won")
                stop 4
            }
            position2 == O {
                print("O Won")
                stop 4
            }
        }
    }
    
    position3 == position6 {
        position6 == position9 {    
            position3 == X {
                print("X Won")
                stop 4
            }
            position3 == O {
                print("O Won")
                stop 4
            }
        }
    }
    
    
    // diagonal
    position1 == position5 {
        position5 == position9 {    
            position1 == X {
                print("X Won")
                stop 4
            }
            position1 == O {
                print("O Won")
                stop 4
            }
        }
    }
    
    position7 == position5 {
        position5 == position3 {    
            position7 == X {
                print("X Won")
                stop 4
            }
            position7 == O {
                print("O Won)
                stop 4
            }
        }
    }
    
    
    
    // Getting User Input
    loop {
        print("Type a position")
        scan(number, position)
        
        position == 1 {
            position1 == EMPTY {
                position1 = turns % 2
                stop 3
            }
        }
        position == 2 {
            position2 == EMPTY {
                position2 = turns % 2
                stop 3
            }
        }
        position == 3 {
            position3 == EMPTY {
                position3 = turns % 2
                stop 3
            }
        }
        position == 4 {
            position4 == EMPTY {
                position4 = turns % 2
                stop 3
            }
        }
        position == 5 {
            position5 == EMPTY {
                position5 = turns % 2
                stop 3
            }
        }
        position == 6 {
            position6 == EMPTY {
                position6 = turns % 2
                stop 3
            }
        }
        position == 7 {
            position7 == EMPTY {
                position7 = turns % 2
                stop 3
            }
        }
        position == 8 {
            position8 == EMPTY {
                position8 = turns % 2
                stop 3
            }
        }
        position == 9 {
            position9 == EMPTY {
                position9 = turns % 2
                stop 3
            }
        }
        
        print("BAD INPUT")
    }
    
    turns = turns + 1
    turns == 9 {
        print("TIE!")
        stop 2
    }
}

print("GAMEOVER")