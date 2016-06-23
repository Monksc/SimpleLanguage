text hello = "Hello"
text space = " "
text world = "World"

text helloWorld = hello + space + world

helloWorld = helloWorld + space + helloWorld

number foo = 4.6
number bar = 0.4
number fooBar = foo + bar

number i = 10

loop {
    
    i == 0 {
        stop 2
    }

    print(i)
    
    i = i - 1
}

print(hello)
print(world)
print(helloWorld)

print()

print(foo)
print(bar)
print(fooBar)