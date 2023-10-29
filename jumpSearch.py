import math
def jumpStart(array,target):
    '''
    - The jumpsart algorithms
    - Time complexity O(n^(1/2))
    
    '''


    jump = int(math.sqrt(len(array)))

    i = 0
    
    while (i+jump<len(array)):

        if (array[i+jump]<=target):
            if array[i+jump] == target:
                return array[i+jump]
            
            else:
                i+=jump

        else:
              break

    for t in range(i,len(array)):
         print(t)

         if array[t] == target:
              return array[t]
         
         if array[t]>target:
              return [-1]
         
    return [-1]
        
             


   
        

# testing

numbers = [1,10,11,20,31,71,99,120,121,200]

print(jumpStart(numbers,-100))
