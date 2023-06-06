import subprocess

# This script calls gradle with the arguments passed to it.
# gradle run --args="arg1 arg2 ... argN"

if __name__ == "__main__":
    planetation = 0.05
    map_size = 20
    stars_quantity = 2
    black_holes_quantity = 2
    aggresive_civilisations_quantity = 5
    pacifistic_civilisations_quantity = 5
    number_of_eras = 1000
    number_of_repeats = 1

    call_text = 'gradle run --args="{} {} {} {} {} {} {}" >> wyniki.txt'.format(planetation, map_size, black_holes_quantity,
                                                            aggresive_civilisations_quantity, pacifistic_civilisations_quantity,
                                                            number_of_eras, number_of_repeats)
    return_code = subprocess.call(call_text, shell=True)
    print(call_text)