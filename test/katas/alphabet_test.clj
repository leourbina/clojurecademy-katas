(ns katas.alphabet-test
  (:require [katas.alphabet :refer :all]
            [midje.sweet :refer :all]))

(facts "About alphabet cyphers"
  (fact "Can encode"
    (encode "vigilance" "meetmeontuesdayeveningatseven")
    => "hmkbxebpxpmyllyrxiiqtoltfgzzv"

    (encode "scones" "meetmebythetree")
    => "egsgqwtahuiljgs")

  (fact "Can decode"
    (decode "vigilance" "hmkbxebpxpmyllyrxiiqtoltfgzzv")
    => "meetmeontuesdayeveningatseven"

    (decode "scones" "egsgqwtahuiljgs")
    => "meetmebythetree")

  (fact "Can decipher"
    (decipher "opkyfipmfmwcvqoklyhxywgeecpvhelzg" "thequickbrownfoxjumpsoveralazydog")
    => "vigilance"

    (decipher "hcqxqqtqljmlzhwiivgbsapaiwcenmyu" "packmyboxwithfivedozenliquorjugs")
    => "scones"))
