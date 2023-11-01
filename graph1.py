
class commune:
    '''
    @class commune
    '''

    def __init__(self, name,center, population):

        self.name = name
        self.population = population
        self.center = center

    def __str__(self):

        return f" {self.name}"
    



class province:

    def __init__(self, name, center):

        self.name = name
        self.center = center
        self.communes = {}

    
    def __str__(self):

        return f"{self.name}"
    

    def addCommune(self,commune):
