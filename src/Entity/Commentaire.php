<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire", indexes={@ORM\Index(name="fk_idpub", columns={"id_publication"})})
 * @ORM\Entity
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_comment", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idComment;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=200, nullable=false)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 5,
     *      max = 50,
     *      minMessage = "Your message must be at least {{ limit }} characters long",
     *      maxMessage = "Your message cannot be longer than {{ limit }} characters"
     * )
     * @Assert\Regex(
     *     pattern="/^[A-Z]/",
     *     match=true,
     *     message="Votre message doit commencer par une lettre majuscule"
     * )
     * @Assert\Regex(
     *     pattern = "/[#?!@$%^&*-]+/i",
     *     match=false,
     *     message="Votre message ne doit pas contenir un caractére spéciale" 
     * )
     */
    private $description;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUser", referencedColumnName="id")
     * })
     */
    private $idUser;

    /**
     * @var \Publication
     *
     * @ORM\ManyToOne(targetEntity="Publication")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_publication", referencedColumnName="idPost")
     * })
     */
    private $idPublication;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=true)
     * 
     */
    private $date;

    public function getIdComment(): ?int
    {
        return $this->idComment;
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

    public function getIdUser(): ?User
    {
        return $this->idUser;
    }

    public function setIdUser(User $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getIdPublication(): ?Publication
    {
        return $this->idPublication;
    }

    public function setIdPublication(?Publication $idPublication): self
    {
        $this->idPublication = $idPublication;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }

}
