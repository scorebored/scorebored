import subprocess
import sys
import getopt
import random

player_one_serving = False
player_one_score = 0
player_two_score = 0

def main():
    global player_one_serving
    global player_one_score
    global player_two_score
    player_one_serving = False
    player_one_score = 0
    player_two_score = 0
    playerInput = raw_input("Enter the Players(separate by comma): ")
    who_is_serving = raw_input("Enter who serves first: ")

    playerInput = playerInput.split(",")
    player_one = playerInput[0].strip()
    player_two = playerInput[1].strip()
    
    subprocess.call("say Todays matchup, %s versus %s" % (player_one, player_two), shell=True)

    subprocess.call("say %s serves first" % (who_is_serving.strip()), shell=True)

    if(who_is_serving == player_one):
        player_one_serving = True

    while(1):
        scoreInput = ''       
        scoreInput = raw_input("Enter 1 or 2, for 1st or 2nd player: ")
        #announce point
        if(scoreInput == '1'):
            subprocess.call("say Point %s" % (player_one), shell=True)
            player_one_score += 1
        elif(scoreInput == '-1'):
            player_one_score -= 1
            say_score()
            continue
        elif(scoreInput == '2'):
            subprocess.call("say Point %s" % (player_two), shell=True)
            player_two_score += 1
        elif(scoreInput == '-2'):
            player_two_score -= 1
            say_score()
            continue
        elif(scoreInput =='3'):
            say_score()
            continue
        elif(scoreInput =='4'):
            random_jacob_excuse()
            continue
        elif(scoreInput =='5'):
            subprocess.call("say %s is serving" % who_is_serving , shell=True)
            continue
        else:
            print "not a correct value"
            
        #check for match point
        if(player_one_score >= 21 and player_one_score - player_two_score >= 2):
            subprocess.call("say Congratulations %s, You have Defeated %s" % (player_one, player_two),
                            shell=True)
            #clear score and start loop and agian and add new game
            break
        if(player_two_score >= 21 and player_two_score - player_one_score >= 2):
            subprocess.call("say Congratulations %s, You have Defeated %s" % (player_two, player_one),
                            shell=True)
            #clear score and start loop and agian and add new game
            break
        
        #check to change servers
        if((player_one_score + player_two_score)%5 == 0):
            subprocess.call("say Change Servers!" , shell=True)
            player_one_serving = not player_one_serving
            if(player_one_serving):
                subprocess.call("say %s is serving" % player_one , shell=True)
                who_is_serving = player_one
            else:
                subprocess.call("say %s is serving" % player_two , shell=True)
                who_is_serving = player_two

        #announce the score base on who is serving
        say_score()
                
        #check for match point
        if(player_one_score >= 20 and player_one_score - player_two_score >= 1):
            subprocess.call("say Game Point %s" % (player_one), shell=True)
        if(player_two_score >= 20 and player_two_score - player_one_score >= 1):
            subprocess.call("say Game Point %s" % (player_two), shell=True)

def say_score():
    global player_one_serving
    global player_one_score
    global player_two_score
    
    if(player_one_score == player_two_score):
        if(player_one_score == 10):
            subprocess.call("say The Score is Ken, all" % (player_one_score), shell=True)
        else:
            subprocess.call("say The Score is %s, all" % (player_one_score), shell=True)
    
    elif(player_one_serving):
        if(player_one_score == 10 or player_two_score == 10):
            if(player_one_score == 10):
                subprocess.call("say The Score is Ken to %s" % (player_two_score),
                            shell=True)
            else:
                subprocess.call("say The Score is %s to Ken" % (player_one_score),
                            shell=True)                 
        else:
            subprocess.call("say The Score is %s to %s" % (player_one_score, player_two_score),
                            shell=True)              
    else:
        if(player_one_score == 10 or player_two_score == 10):
            if(player_one_score == 10):
                subprocess.call("say The Score is %s to Ken" % (player_two_score),
                            shell=True)
            else:
                subprocess.call("say The Score is Ken to %s" % (player_one_score),
                            shell=True)                 
        else:
            subprocess.call("say The Score is %s to %s" % (player_two_score, player_one_score),
                            shell=True)
def random_jacob_excuse():
    jacob_list = ['Your shirt is white, cant see the ball when its in front of your shirt.',
                  'My back hurts ',
                  'I let him win',
                  'The lighting was bad',
                  'I was practicing my swing, and was not playing serious.',
                  'It is Tuesday',
                  'I was blowing my thing',
                  'These balls are too big',
                  'I thought he was Jeff',
                  'My wrist hurts',]
    subprocess.call("say " + str(jacob_list[random.randint(0,len(jacob_list) - 1)]), shell=True)
            

if __name__ == '__main__':
    main()

            
            
            
            
            
            
            
            
