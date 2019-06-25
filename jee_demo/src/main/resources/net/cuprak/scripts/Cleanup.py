class Cleanup(object):
    print ("Creating new Class\n===============")

    def cleanup(self,participant):
        participant.setLastName(participant.getName())
        print("cleanup!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


def create() :
    return Cleanup()