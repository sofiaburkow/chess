**Sjakk**

I forbindelse med semesteroppgave 2 valgte jeg åpen oppgave og å programmere sjakk.
Jeg startet ikke helt fra scratch, men tok i bruk grid-klassene og de tilhørende testene til grid
fra koden til Blob Wars. Tok også en god del inspirasjon fra GUI-klassene til Blob Wars når
jeg lagde min egen GUI. 

_Regler_

Sjakk-spillet jeg har programmert følger i all hovedsak de offisielle spillreglene for sjakk.
Dessverre er det slik at jeg ikke rakk å implementere alle reglene før semesteroppgaven måtte inn.
Jeg rakk blant annet ikke å implementere "pawn promotion". Det er når en bonde når motsatt ende av brettet
og enten erstattes av en dronning, tårn, løper eller hest. I tillegg rakk jeg ikke å fikse alle de ulike
måtene spillet kan ende på. Rakk kun å programmere checkmate og stalemate.

_How to play_

For å flytte en brikke må man først trykke på brikken man ønsker å flytte, for så å trykke på flisen man ønsker 
å flytte til, eller brikken man ønsker å slå ut. Det vil vises på brettet hvor man kan flytte / gyldige trekk. 
Partiet er som tidligere nevnt først over om det oppstår checkmate eller stalemate.

_Arkitektur_

Når det kommer til arkitektur har jeg tatt mye inspirasjon både fra Tetris og Blob Wars. Prøvde å lage noe lignende 
til GUI-pakken fra Blob Wars, men synes ikke selv at jeg fikk det helt til. Det er mye av grunnen til at game over
kun printes i terminalen. Jeg slet nemlig litt med å finne ut hvordan jeg skulle få det printet på skjermen ut
fra hvordan jeg hadde satt opp koden min. Tror også det hadde lønnet seg om jeg lagde en game-loop i stedet for å
løse spillet i ClickableBoard klassen som jeg endte opp med. 

Jeg var også lenge usikker på om jeg skulle ha en egen ChessModel-klasse, men ente til slutt opp med å heller lage
et felles restriktivt grensesnitt IBord som begrenset GUI-en og brikkenes tilgang til grid-metodene og
ChessBoard-metodene. 

Jevn over er det nok en del ting jeg ville ha løst annerledes om jeg skulle ha laget koden på nytt. Det vanskeligste 
for meg med kodingen var ikke logikken bak sjakk (med unntak av en Passant), men heller hvordan jeg skulle sette
opp og designe spillet. Vet at jeg trolig ville ha reddet inn en bedre poengsum om jeg valgte å gå for Blob Wars, 
men hadde nok ikke lært like mye som jeg gjorde nå ved å velge åpen oppgave. 
