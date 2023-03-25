<?php

namespace App\Entity;

use App\Repository\BonplanRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

#[ORM\Entity(repositoryClass: BonplanRepository::class)]
class Bonplan
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name:"id_bonplan")]
    private ?int $id = null;

    #[ORM\Column(length: 50)]
    #[Assert\NotBlank(message:"Le nom du bon plan est obligatoire !")]
    private ?string $nom_bonplan = null;

    #[ORM\Column(length: 50)]
    private ?string $gouvernorat = null;

    #[ORM\Column(length: 50)]
    private ?string $ville = null;

    #[ORM\Column(length: 255)]
    private ?string $rue = null;

    #[ORM\Column(length: 255)]
    private ?string $description = null;

    #[ORM\Column(length: 50)]
    private ?string $type = null;

    #[ORM\Column(length: 20)]
    private ?string $etat = null;

    #[ORM\Column(length: 255)]
    private ?string $image = null;

    #[ORM\ManyToOne]
    #[ORM\JoinColumn(nullable: false,name:"id_user",referencedColumnName:"id_user")]
    private ?User $user = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomBonplan(): ?string
    {
        return $this->nom_bonplan;
    }

    public function setNomBonplan(string $nom_bonplan): self
    {
        $this->nom_bonplan = $nom_bonplan;

        return $this;
    }

    public function getGouvernorat(): ?string
    {
        return $this->gouvernorat;
    }

    public function setGouvernorat(string $gouvernorat): self
    {
        $this->gouvernorat = $gouvernorat;

        return $this;
    }

    public function getVille(): ?string
    {
        return $this->ville;
    }

    public function setVille(string $ville): self
    {
        $this->ville = $ville;

        return $this;
    }

    public function getRue(): ?string
    {
        return $this->rue;
    }

    public function setRue(string $rue): self
    {
        $this->rue = $rue;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

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

    public function getUser(): ?User
    {
        return $this->user;
    }

    public function setUser(?User $user): self
    {
        $this->user = $user;

        return $this;
    }
}
