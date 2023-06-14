# Symulacja podboju kosmosu - Programowanie Obiektowe 2023

Projekt został stworzony przez dwóch super ambitnych, pierwszorocznych studentów, Krzysztofa i Piotra, w ramach ćwiczeń z kursu programowania obiektowego.

Podstawą projektu jest dwuwymiarowa mapa zawierająca w sobie różne ciała niebieskie:
1) planety - posiadające losową ilość zasobów początkowych z danego przedziału, z których cywilizacje pobierają zasoby, jeśli planeta jest w ich posiadaniu
2) czarne dziury - wciąga statki, które pojawiają się w jej zasięgu
3) gwiazdy - wzmacniają parametry dla cywilizacji pacyfistycznych mnożąc ich zdolność wydobywania zasobów w ciągu iteracji symulacji

Planety mogą być w posiadaniu jednego z dwóch rodzajów cywilizacji:
    1) pacyfistycznej - ich parametry mogą zostać wzmocnione przez gwiazdy; nie są w stanie przejąć zajętej już planety; zielone elementy w wizualizacji  
    2) agresywnej - ich statki są w stanie przejmować zajęte już planety; czerwone elementy w wizualizacji

Podstawową cechą charakterystyczną dla cywilizacji w danym momencie jest ilość wydobytych przez nią zasobów. Dzięki tym zasobom jest w stanie ona się rozwijać i wysyłać statki zasiedlające nowe planety. 

Przykładowymi mierzonymi parametrami jest rozwój cywilizacji rozumiany np. 
przez to kto zajął więcej planet,
kto wydobył więcej zasobów, kto wysłał ile statków.

Statki poruszają się do danej planety, która jest losowana spośród 3 najbliższych planet (obliczonych dla planety z której statek jest wysyłany z wykorzystaniem fitness wheel proportion (prawdopodobieństwo wybrania planety położonej najbliżej jest największe). Gdy statek nie może poruszyć się bliżej wybranej planety przez 3 tury to losuje planetę z pustych planet.


Każda z planet, aktualnie zajęta przez daną cywilizację oraz posiadająca wymaganą ilość zasobów, wysyła do przestrzeni statek, który po wleceniu w obszar innej planety:
Gdy planeta jest pusta:
W przypadku obu rodzajów statków zostanie ona przejęta przez statek.
Gdy planeta jest zajęta przez cywilizację Pacyfistyczną:
Jeśli przyleci na nią statek pacyfistyczny to zostanie przekierowana do innej planety i uzupełni swoje paliwo
Jeśli przyleci na nią statek agresywny to spróbuje ją przejąć (uda mu się gdy jego atak będzie większy niż 0.7 * zasoby atakowanej cywilizacji
Gdy planeta jest zajęta przez cywilizację Agresywną:
Jeśli przyleci na nią statek pacyfistyczny lub obcy statek agresywny to zostanie zniszczony
Jeśli przyleci na nią statek agresywny spod tej samej cywilizacji to zostanie przekierowany do innej planety i uzupełni swoje paliwo
Statki powinny parametr typu paliwo i po pewnym czasie dezintegrują się w
pustce zostawiając za sobą jedynie cień dawnej świetności [*]