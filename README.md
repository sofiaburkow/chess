Sjakk

I forbindelse med semesteroppgave 2 valgte jeg åpen oppgave og å programmere sjakk.
Jeg startet ikke helt fra scratch, men tok i bruk grid-klassene og de tilhørende testene
fra koden til Blob Wars. Tok også en god del inspirasjon fra GUI-klassene til Blob Wars når
jeg skrev koden for grafikken til sjakk-spillet. 

Regler

Sjakk-spillet jeg har programmert følger i all hovedsak de offisielle spillreglene for sjakk.
Dessverre er det slik at jeg ikke rakk å implementere alle reglene før semesteroppgaven måtte inn.
Jeg rakk blant annet ikke å implementere "pawn promotion". Det er når en bonde når motsatt ende av brettet
og enten erstattes av en dronning, tårn, løper eller hest avhengig av hva spilleren ønsker. I tillegg rakk
jeg ikke å fikse alle de ulike måtene spillet kan ende på. Jeg rakk å programmere checkmate, og
stalemate, men ikke de resterende måtene sjakk kan ende i remi eller seier til en av partene. La heller ikke 
inn en funksjon for at en av spillerne velger å gi seg.

How to play

For å flytte en brikke må man først trykke med musen på brikken man ønsker å flytte, for så å trykke på flisen man ønsker 
å flytte til, eller brikken man ønsker å slå ut. Det vil vises på brettet hvor man kan flytte / gyldige trekk. Partiet er 
som tidligere nevnt først over om det oppstår sjakkmatt eller stalemate. 


Arkitektur

Når det kommer til arkitektur har jeg dratt tatt mye inspirasjon både fra tetris og blob wars.
Til å begynne med hadde jeg en klasse ChessModel som hadde en feltvariabel av typen chessBoard 
(lik måte å sette opp koden som i tetris), men på et tidspunkt syntes jeg at det virket unødvendig med 
begge to, så fjernet ChessModel og valgte å implementere alle metodene i 


Grid-klassene har flere metoder enn de jeg tar i bruk. Burde nok ha skrevet egen kode her.
