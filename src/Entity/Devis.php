<?php

namespace App\Entity;

use App\Repository\DevisRepository;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: DevisRepository::class)]
class Devis
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column(name:"id_devis")]
    private ?int $id = null;

    #[ORM\ManyToOne]
    #[ORM\JoinColumn(nullable: false,name:"id_user",referencedColumnName:"id_user")]
    private ?User $user = null;

    #[ORM\Column]
    private ?int $TVA = null;

    #[ORM\Column]
    private ?float $total = null;

    #[ORM\ManyToOne]
    #[ORM\JoinColumn(nullable: false,name:"id_garage",referencedColumnName:"id_garage")]
    private ?Garagec $garage = null;

    #[ORM\ManyToOne]
    #[ORM\JoinColumn(nullable: false,name:"id_maintenance",referencedColumnName:"id_maintenance")]
    private ?Maintenance $maintenance = null;

    public function getId(): ?int
    {
        return $this->id;
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

    public function getTVA(): ?int
    {
        return $this->TVA;
    }

    public function setTVA(int $TVA): self
    {
        $this->TVA = $TVA;

        return $this;
    }

    public function getTotal(): ?float
    {
        return $this->total;
    }

    public function setTotal(float $total): self
    {
        $this->total = $total;

        return $this;
    }

    public function getGarage(): ?Garagec
    {
        return $this->garage;
    }

    public function setGarage(?Garagec $garage): self
    {
        $this->garage = $garage;

        return $this;
    }

    public function getMaintenance(): ?Maintenance
    {
        return $this->maintenance;
    }

    public function setMaintenance(?Maintenance $maintenance): self
    {
        $this->maintenance = $maintenance;

        return $this;
    }
}
