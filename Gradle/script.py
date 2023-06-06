import subprocess
import os
# This script calls gradle with the arguments passed to it.
# gradle run --args="arg1 arg2 ... argN"

if __name__ == "__main__":
    planetation = 0.1 #skok 0.001
    map_size = 20
    stars_quantity = 2
    black_holes_quantity = 2
    aggresive_civilisations_quantity = 5
    pacifistic_civilisations_quantity = 5
    number_of_eras = 1000
    number_of_repeats = 1

    # check if there is enough planets for all civilisations!

    call_text = 'gradlew run --args="{} {} {} {} {} {} {} {}"'.format(planetation, map_size, stars_quantity, black_holes_quantity,
                                                            aggresive_civilisations_quantity, pacifistic_civilisations_quantity,
                                                            number_of_eras, number_of_repeats)
    

    return_code = subprocess.check_output(call_text, shell=True)

    return_code = return_code.decode("utf-8")
    # print only line begining with "Return code:"
    split_code = return_code.splitlines()
    
    result_for_file = ""
    
    for i in range(5, 6 + pacifistic_civilisations_quantity + aggresive_civilisations_quantity):
        # print(split_code[i])
        result_for_file += split_code[i] + "\n"
        # subprocess.run('echo ' + split_code[i] +' >> results.txt', shell=True)
    
    result_for_file += "\n"
    
    with open("results.txt", "a") as myfile:
        myfile.write(result_for_file)

    # subprocess.run('echo ' + '\"' + result_for_file + '\"' +' >> results.txt', shell=True)
    print(result_for_file)
    print(call_text)