<?php

namespace App\Entity;

use App\Repository\GaragecRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: GaragecRepository::class)]
class Garagec
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name:"id_garage")]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    private ?string $nom_garage = null;

    #[ORM\Column(length: 255)]
    private ?string $adresse = null;

    #[ORM\Column]
    private ?int $numero = null;

    #[ORM\Column]
    private ?int $panne_moteur = null;

    #[ORM\Column]
    private ?int $pompe_a_eau = null;

    #[ORM\Column]
    private ?int $patin = null;

    #[ORM\Column]
    private ?int $essuie_glace = null;

    #[ORM\Column]
    private ?int $radiateur = null;

    #[ORM\Column]
    private ?int $ventilateur = null;

    #[ORM\Column]
    private ?int $duride = null;

    #[ORM\Column]
    private ?int $fuite_d_huile = null;

    #[ORM\Column]
    private ?int $vidange = null;

    #[ORM\Column]
    private ?int $filtre = null;

    #[ORM\Column]
    private ?int $batterie = null;

    #[ORM\Column]
    private ?int $amortisseur = null;

    #[ORM\Column]
    private ?int $frein_main = null;

    #[ORM\Column]
    private ?int $feu_d_eclairage = null;

    #[ORM\Column]
    private ?int $taux_de_reduction = null;

    #[ORM\Column(length: 255)]
    private ?string $image = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomGarage(): ?string
    {
        return $this->nom_garage;
    }

    public function setNomGarage(string $nom_garage): self
    {
        $this->nom_garage = $nom_garage;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getNumero(): ?int
    {
        return $this->numero;
    }

    public function setNumero(int $numero): self
    {
        $this->numero = $numero;

        return $this;
    }

    public function getPanneMoteur(): ?int
    {
        return $this->panne_moteur;
    }

    public function setPanneMoteur(int $panne_moteur): self
    {
        $this->panne_moteur = $panne_moteur;

        return $this;
    }

    public function getPompeAEau(): ?int
    {
        return $this->pompe_a_eau;
    }

    public function setPompeAEau(int $pompe_a_eau): self
    {
        $this->pompe_a_eau = $pompe_a_eau;

        return $this;
    }

    public function getPatin(): ?int
    {
        return $this->patin;
    }

    public function setPatin(int $patin): self
    {
        $this->patin = $patin;

        return $this;
    }

    public function getEssuieGlace(): ?int
    {
        return $this->essuie_glace;
    }

    public function setEssuieGlace(int $essuie_glace): self
    {
        $this->essuie_glace = $essuie_glace;

        return $this;
    }

    public function getRadiateur(): ?int
    {
        return $this->radiateur;
    }

    public function setRadiateur(int $radiateur): self
    {
        $this->radiateur = $radiateur;

        return $this;
    }

    public function getVentilateur(): ?int
    {
        return $this->ventilateur;
    }

    public function setVentilateur(int $ventilateur): self
    {
        $this->ventilateur = $ventilateur;

        return $this;
    }

    public function getDuride(): ?int
    {
        return $this->duride;
    }

    public function setDuride(int $duride): self
    {
        $this->duride = $duride;

        return $this;
    }

    public function getFuiteDHuile(): ?int
    {
        return $this->fuite_d_huile;
    }

    public function setFuiteDHuile(int $fuite_d_huile): self
    {
        $this->fuite_d_huile = $fuite_d_huile;

        return $this;
    }

    public function getVidange(): ?int
    {
        return $this->vidange;
    }

    public function setVidange(int $vidange): self
    {
        $this->vidange = $vidange;

        return $this;
    }

    public function getFiltre(): ?int
    {
        return $this->filtre;
    }

    public function setFiltre(int $filtre): self
    {
        $this->filtre = $filtre;

        return $this;
    }

    public function getBatterie(): ?int
    {
        return $this->batterie;
    }

    public function setBatterie(int $batterie): self
    {
        $this->batterie = $batterie;

        return $this;
    }

    public function getAmortisseur(): ?int
    {
        return $this->amortisseur;
    }

    public function setAmortisseur(int $amortisseur): self
    {
        $this->amortisseur = $amortisseur;

        return $this;
    }

    public function getFreinMain(): ?int
    {
        return $this->frein_main;
    }

    public function setFreinMain(int $frein_main): self
    {
        $this->frein_main = $frein_main;

        return $this;
    }

    public function getFeuDEclairage(): ?int
    {
        return $this->feu_d_eclairage;
    }

    public function setFeuDEclairage(int $feu_d_eclairage): self
    {
        $this->feu_d_eclairage = $feu_d_eclairage;

        return $this;
    }

    public function getTauxDeReduction(): ?int
    {
        return $this->taux_de_reduction;
    }

    public function setTauxDeReduction(int $taux_de_reduction): self
    {
        $this->taux_de_reduction = $taux_de_reduction;

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(string $image): self
    {
        $this->image = $image;

        return $this;
    }
}
