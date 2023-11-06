

class Node:

    def __init__(self, data):
        self.data = data
        self.next = None
        self.prev = None

    def __str__(self):

        return f"{self.data}"
    

class doublyLinked:
    
    def __init__(self):
        self.head = None
        self.tail = None

    
    def append(self,node):


        node = Node(node)

        if self.head == None:

            self.head =node
            self.tail = self.head
            return
        
        curr_node = self.head

        while curr_node.next:
            curr_node = curr_node.next
            
        curr_node.next = node
        node.prev = curr_node
        self.tail = node


    def __str__(self):

        curr_node = self.head

        linked = "|"

        while curr_node.next:

            linked = linked+str(curr_node.data)+"--->"
            curr_node = curr_node.next


        return linked+str(curr_node.data)+"|"
    




def evaluate(string):
   '''
   - Takes a string of parantheses
   - Verify is it matches
   '''

   symbol = {")":"(","]":"[","}":"{"}

   stack = []

   for i in string:
       
       
           
           if i in symbol and len(stack)!=0:
               
               if symbol[i] == stack[-1]:
                   stack.pop()
               else:
                   stack.append(i)

           else:
               stack.append(i)


   return len(stack)==0



value = "()[]{}(])"

print(evaluate(value))


# evaluate short expresion

def eval(string):

    operators = {"*":"*","+": "+", "-":"-"}

    for i in string:

        if i in operators:

            

           
           







# testing

a = doublyLinked()
a.append(10)
a.append(20)
a.append(40)
print(a)




    

