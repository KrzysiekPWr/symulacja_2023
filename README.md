# Symulacja podboju kosmosu - Programowanie Obiektowe 2023

Projekt został stworzony przez dwóch pierwszorocznych studentów, Krzysztofa i Piotra, w ramach laboratorium z kursu Programowania Obiektowego.

**Wideo z symulacji:**
<p align="center">
<a href="http://www.youtube.com/watch?feature=player_embedded&v=u13-Jf2PLQc&ab_channel=PiotrKoronczok
" target="_blank"><img src="http://img.youtube.com/vi/u13-Jf2PLQc/0.jpg" 
alt="IMAGE ALT TEXT HERE" width="480" height="360" border="10" /></a>

</p>

Podstawą projektu jest dwuwymiarowa mapa zawierająca w sobie różne ciała niebieskie:
planety - posiadające losową ilość zasobów początkowych z danego przedziału, z których cywilizacje pobierają zasoby, jeśli planeta jest w ich posiadaniu
* czarne dziury - wciąga statki, które pojawiają się w jej zasięgu
* gwiazdy - wzmacniają parametry dla cywilizacji pacyfistycznych mnożąc ich zdolność wydobywania zasobów w ciągu iteracji symulacji

Planety mogą być w posiadaniu jednego z dwóch rodzajów cywilizacji:
* pacyfistycznej - ich parametry mogą zostać wzmocnione przez gwiazdy; nie są w stanie przejąć zajętej już planety; zielone elementy w wizualizacji
* agresywnej - ich statki są w stanie przejmować zajęte już planety; czerwone elementy w wizualizacji

Podstawową cechą charakterystyczną dla cywilizacji w danym momencie jest ilość wydobytych przez nią zasobów. Dzięki tym zasobom jest w stanie ona się rozwijać i wysyłać statki zasiedlające nowe planety. 

Przykładowymi mierzonymi parametrami jest rozwój cywilizacji rozumiany np. 
przez to kto zajął więcej planet, kto wydobył więcej zasobów, kto wysłał ile statków.

Statki poruszają się do danej planety, która jest losowana spośród 3 najbliższych planet, obliczonych dla planety z której statek jest wysyłany z wykorzystaniem fitness wheel proportion (prawdopodobieństwo wybrania planety położonej najbliżej jest największe). Gdy statek nie może poruszyć się bliżej wybranej planety przez 3 tury to losuje planetę z pustych planet.

Każda z planet, aktualnie zajęta przez daną cywilizację oraz posiadająca wymaganą ilość zasobów, wysyła do przestrzeni statek, który po wleceniu w obszar innej planety:
* Gdy planeta jest pusta:
    * W przypadku obu rodzajów statków zostanie ona przejęta przez statek.

* Gdy planeta jest zajęta przez cywilizację Pacyfistyczną jeśli:
    * przyleci na nią statek pacyfistyczny to zostanie przekierowana do innej planety i uzupełni swoje paliwo
    * przyleci na nią statek agresywny to spróbuje ją przejąć (uda mu się gdy jego atak będzie większy niż 0.7 * zasoby atakowanej cywilizacji

* Gdy planeta jest zajęta przez cywilizację Agresywną jeśli:
    * przyleci na nią statek pacyfistyczny lub obcy statek agresywny to zostanie zniszczony
    * przyleci na nią statek agresywny spod tej samej cywilizacji to zostanie przekierowany do innej planety i uzupełni swoje paliwo


Własności statków: 
* określony zasięg przez ilość paliwa
* szybkość z jaką mogą się poruszać
* cena statku - ile cywilizacja musi wydać na statek
* dodatkowo statki agresywne mają określoną siłę ataku, która musi być większa niż 0.7 zasobów cywilizacji planety atakowanej, aby ta * * * została przejęta przez najeźdźcę
* statek pacyfistyczny, gdy trafi na planetę zajętą przez inną cywilizację pacyfistyczną, zmieni kierunek i uzupełni zapasy paliwa

Co dalej? Pomysły:
* Stworzyć skrypt, który dodatkowo tworzy za użytkownika odpowiednie wykresy i wpisuje dane do excela
* Dodać randomizer nazw cywilizacji
* Dodać część interaktywną dla użytkownika podczas symulacji (pokazywanie informacji o cywilizacji lub planetach po najechaniu na nie kursorem)
* Dodać algorytm trasowania
* Ulepszyć walkę pomiędzy agresywnymi cywilizacjami
* Dodać walkę pomiędzy statkami
* Dodać animację statków, przejmowania i degradacji planet podczas wydobywania
* Dodać meteory, wormhole
* Opisać dokładnie wszystkie metody i klasy w komentarzach (w kodzie)
* Dokładniej przeanalizować dane i wykresy, znaleźć ciekawe kombinacje wartości początkowych
* I wiele wiele innych…
