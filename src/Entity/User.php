<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: UserRepository::class)]
class User
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id_user;

    #[ORM\Column(length: 50)]
    private ?string $nom ;

    #[ORM\Column(length: 50)]
    private ?string $prenom ;

    #[ORM\Column(length: 100)]
    private ?string $mdp ;

    #[ORM\Column(length: 100)]
    private ?string $email ;

    #[ORM\Column]
    private ?int $num_tel ;

    #[ORM\Column]
    private ?int $cin ;

    #[ORM\Column]
    private ?float $quota ;

    #[ORM\Column(length: 255)]
    private ?string $img ;

    #[ORM\ManyToOne]
    #[ORM\JoinColumn(nullable: false,name:"id_role",referencedColumnName:"id_role")]
    private ?Role $id_role ;

    #[ORM\Column]
    private ?int $etat ;

    public function getIdUser(): ?int
    {
        return $this->id_user;
    }
    public function setIdUser(int $id): self
    {
        $this->id_user = $id;

        return $this;
    }
    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getMdp(): ?string
    {
        return $this->mdp;
    }

    public function setMdp(string $mdp): self
    {
        $this->mdp = $mdp;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getNumTel(): ?int
    {
        return $this->num_tel;
    }

    public function setNumTel(int $num_tel): self
    {
        $this->num_tel = $num_tel;

        return $this;
    }

    public function getCin(): ?int
    {
        return $this->cin;
    }

    public function setCin(int $cin): self
    {
        $this->cin = $cin;

        return $this;
    }

    public function getQuota(): ?float
    {
        return $this->quota;
    }

    public function setQuota(float $quota): self
    {
        $this->quota = $quota;

        return $this;
    }

    public function getImg(): ?string
    {
        return $this->img;
    }

    public function setImg(string $img): self
    {
        $this->img = $img;

        return $this;
    }

    public function getIdRole(): ?Role
    {
        return $this->id_role;
    }

    public function setIdRole(?Role $id_role): self
    {
        $this->id_role = $id_role;

        return $this;
    }

    public function getEtat(): ?int
    {
        return $this->etat;
    }

    public function setEtat(int $etat): self
    {
        $this->etat = $etat;

        return $this;
    }
}
