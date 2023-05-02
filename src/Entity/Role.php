<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Role
 *
 */
#[ORM\Table(name: 'role')]
#[ORM\Entity]
class Role
{
    /**
     * @var int
     *
     */
    #[ORM\Column(name: 'id_role', type: 'integer', nullable: false)]
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: 'IDENTITY')]
    #[Groups("user")]
    private $idRole;

    /**
     * @var string
     *
     */
    #[ORM\Column(name: 'type_role', type: 'string', length: 45, nullable: false)]
    #[Groups("user")]
    private $typeRole;

    public function getIdRole(): ?int
    {
        return $this->idRole;
    }

    /**
     * @param int $idRole
     */
    public function setIdRole(int $idRole): void
    {
        $this->idRole = $idRole;
    }
    public function getTypeRole(): ?string
    {
        return $this->typeRole;
    }

    public function setTypeRole(string $typeRole): self
    {
        $this->typeRole = $typeRole;

        return $this;
    }
    public function __toString(): string
    {
        return $this->getIdRole(); // Replace this with the appropriate property or method that returns the string representation of the Role object
    }

}
