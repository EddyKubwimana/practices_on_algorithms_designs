
class commune:
    '''
    @class commune

    '''

    def __init__(self, name,center, population):

        self.name = name
        self.population = population
        self.center = center
        self.connected = []

    def __str__(self):


        return f" {self.name}"
    
    def addConnectedCommunes(self, commune):
        ''' 
        - Add a communes in the connected communes
        
        '''

        if  not commune in self.connected:
            self.connected.append(commune)


    def getBorderingCommunes(self):
        ''' 
        - returns bordering communes

        '''

        return self.connected
    
    



class province:

    def __init__(self, name, center):

        self.name = name
        self.center = center
        self.communes = []
        self.connected =[]

    
    def __str__(self):

        return f"{self.name}"
    

    def addCommune(self,commune):

        '''
        - Add a commune in the province
        
        '''

        if commune in self.communes:
            pass
        else:
            self.communes.append(commune)

    
    def addBorderingProvince(self,province):

        '''
        - Add bordering  province
        
        '''

        if commune in self.connected:
            pass
        else:
            self.connected.append(province)


    def getBorderingProvince(self):

        '''

        - returns bordering provinces
        
        '''

        return self.connected


        
        
