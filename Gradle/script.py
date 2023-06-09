import subprocess
from openpyxl import load_workbook, Workbook
import os

wb = load_workbook(filename='results_simulation.xlsx')

# This script calls gradle with the arguments passed to it.
# gradle run --args="arg1 arg2 ... argN"

if __name__ == "__main__":
    planetation = 0.1 #skok 0.001
    map_size = 20
    stars_quantity = 2
    black_holes_quantity = 2
    aggresive_civilisations_quantity = 5
    pacifistic_civilisations_quantity = 5
    number_of_eras = 500
    number_of_repeats = 1

    # check if there is enough planets for all civilisations!

    call_text = 'gradlew run --args="{} {} {} {} {} {} {} {}"'.format(planetation, map_size, stars_quantity, black_holes_quantity,
                                                            aggresive_civilisations_quantity, pacifistic_civilisations_quantity,
                                                            number_of_eras, number_of_repeats)
    

    return_code = subprocess.check_output(call_text, shell=True)

    return_code = return_code.decode("utf-8")
    # print only line begining with "Return code:"
    split_code = return_code.splitlines()
    

    # code for printing results in manner like:
    # typeOfCiv allResourcesOfCiv 
    # ---------------------------------------------
    #result_for_file = ""
    # for i in range(5, 6 + pacifistic_civilisations_quantity + aggresive_civilisations_quantity):
    #     result_for_file += split_code[i] + "\n"
    # result_for_file += "\n"


    # code for printing results in manner like:
    # idOfCiv TypeOfCiv ResourcesGatheredTillThisEra
    # ---------------------------------------------
    result_for_file = ""
    start = False
    for line in split_code:
        if(line == "-" and start == False):
            start = True
            continue
        elif(line == "-" and start == True):
            break

        if(start == True):
            result_for_file += line + "\n"
        
    wb.create_sheet("Test")
    ws = wb["Test"]
    ws.append(result_for_file)
    wb.save("results_simulation.xlsx")

    
    with open("results_simulation_course.txt", "a") as myfile:
        myfile.write(result_for_file)
        myfile.write("-------------------\n")
        myfile.close()
    
    # print(return_code)
    # print(call_text)