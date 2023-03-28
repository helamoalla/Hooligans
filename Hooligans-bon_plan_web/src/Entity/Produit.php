<?php

namespace App\Entity;

use App\Repository\ProduitRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
#[ORM\Entity(repositoryClass: ProduitRepository::class)]
class Produit
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name:"id_prod")]
    public ?int $id = null;

    #[ORM\Column(length: 30)]
    #[Assert\NotBlank(message:'ce champ est obligatoire')]
    public ?string $nom_prod = null;


    #[ORM\Column]
    #[Assert\NotBlank(message:'ce champ est obligatoire')]
    #[Assert\Positive(message:'le prix doit etre positive')]
    public ?float $prix_prod = null;

    #[ORM\Column(length: 100)]
    #[Assert\NotBlank(message:'ce champ est obligatoire')]
   public ?string $description_prod = null;

    #[ORM\Column]
    #[Assert\NotBlank(message:'ce champ est obligatoire')]
    #[Assert\Positive(message:'la quantite doit etre positive ')]
    public ?int $quantite_prod = null;

    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message:'ce champs est obligatoire')]
    public ?string $image = null;

    #[ORM\ManyToOne]
    #[ORM\JoinColumn(nullable: false,name:"id_categorie",referencedColumnName:"id_categorie")]
    public ?Categorie $categorie = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomProd(): ?string
    {
        return $this->nom_prod;
    }

    public function setNomProd(string $nom_prod): self
    {
        $this->nom_prod = $nom_prod;

        return $this;
    }

    public function getPrixProd(): ?float
    {
        return $this->prix_prod;
    }

    public function setPrixProd(float $prix_prod): self
    {
        $this->prix_prod = $prix_prod;

        return $this;
    }

    public function getDescriptionProd(): ?string
    {
        return $this->description_prod;
    }

    public function setDescriptionProd(string $description_prod): self
    {
        $this->description_prod = $description_prod;

        return $this;
    }

    public function getQuantiteProd(): ?int
    {
        return $this->quantite_prod;
    }

    public function setQuantiteProd(int $quantite_prod): self
    {
        $this->quantite_prod = $quantite_prod;

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

    public function getCategorie(): ?Categorie
    {
        return $this->categorie;
    }

    public function setCategorie(?Categorie $categorie): self
    {
        $this->categorie = $categorie;

        return $this;
    }
}
